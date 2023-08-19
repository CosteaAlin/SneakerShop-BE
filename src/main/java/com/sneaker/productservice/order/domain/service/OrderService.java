package com.sneaker.productservice.order.domain.service;

import com.sneaker.productservice.cart.domain.entity.CartEntity;
import com.sneaker.productservice.cart.domain.model.Cart;
import com.sneaker.productservice.cart.domain.service.CartService;
import com.sneaker.productservice.config.MailService;
import com.sneaker.productservice.customer.domain.entity.CustomerEntity;
import com.sneaker.productservice.customer.domain.service.CustomerService;
import com.sneaker.productservice.exceptions.InsufficientStockException;
import com.sneaker.productservice.inventory.domain.entity.InventoryEntity;
import com.sneaker.productservice.inventory.domain.serivce.InventoryService;
import com.sneaker.productservice.order.domain.entity.OrderEntity;
import com.sneaker.productservice.order.domain.entity.repository.OrderRepository;
import com.sneaker.productservice.order.rest.dto.OrderRequest;
import com.sneaker.productservice.order.rest.dto.OrderResponse;
import com.sneaker.productservice.product.domain.entity.ProductEntity;
import com.sneaker.productservice.product.domain.service.ProductService;
import com.sneaker.productservice.size.domain.entity.SizeEntity;
import com.sneaker.productservice.size.domain.service.SizeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.sneaker.productservice.config.MailService.getCurrentDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final SizeService sizeService;
    private final InventoryService inventoryService;
    private final MailService mailService;
    private static final String TEXT_MESSAGE_TEMPLATE = """
            Dear %s,

            Thank you for shopping with Sneaker Shop!
            We're pleased to confirm your order, and we're getting everything ready for you.
            Here are the details of your purchase:

            Order Number: %d
            Order Date: %s
            Total Amount: %.2f
            
            Thank you again for choosing us. We hope you enjoy your new items!
                        
            Warm regards,
            The Sneaker Shop Team""";

    public OrderService(OrderRepository orderRepository, ProductService productService, InventoryService inventoryService,
                        CustomerService customerService, CartService cartService, SizeService sizeService,
                        MailService mailService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.cartService = cartService;
        this.sizeService = sizeService;
        this.inventoryService = inventoryService;
        this.mailService = mailService;
    }

    @Transactional
    public OrderResponse addOrder(OrderRequest request, String sessionId) {
        CartEntity cart = cartService.getCart(sessionId);
        List<ProductEntity> products = getProducts(cart.getProducts());
        List<Cart> cartItems = getCartItemList(cart.getProducts());
        decreaseStock(cartItems);
        double totalAmount = calculateTotalAmount(cartItems);
        CustomerEntity customer = getCustomer(request);

        OrderEntity order = createOrderEntity(products, customer, totalAmount);
        OrderEntity saved = orderRepository.save(order);

        OrderResponse orderResponse = mapOrderEntityToResponse(saved, totalAmount, customer);
        String textMessage = String.format(TEXT_MESSAGE_TEMPLATE, orderResponse.getName(), orderResponse.getId(),
                getCurrentDateTime(), orderResponse.getTotalAmount());
        mailService.send(customer.getEmail(), textMessage);
        return orderResponse;
    }

    private void decreaseStock(List<Cart> cartItems) {
        for (Cart c : cartItems) {
            SizeEntity sizeEntity = sizeService.getSizeByValue(c.getSize());
            InventoryEntity inventoryEntity = inventoryService.getInventoryEntityByProductAndSize(c.getProductId(),
                    sizeEntity.getId());
            if (inventoryEntity.getQuantity() < c.getQuantity()) {
                throw new InsufficientStockException("Not enough stock available for this product");
            } else {
                int newStock = inventoryEntity.getQuantity() - c.getQuantity();
                inventoryEntity.setQuantity(newStock);
                inventoryService.save(inventoryEntity);
            }
        }
    }

    private CustomerEntity getCustomer(OrderRequest request) {
        Optional<CustomerEntity> optionalCustomer = customerService.getCustomerByEmail(request.getEmail());
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            CustomerEntity newCustomer = createCustomer(request);
            return customerService.save(newCustomer);
        }
    }

    private CustomerEntity createCustomer(OrderRequest request) {
        CustomerEntity customer = new CustomerEntity();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setAddress(request.getAddress());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        return customer;
    }

    private OrderResponse mapOrderEntityToResponse(OrderEntity entity, double totalAmount, CustomerEntity customer) {
        String fullName = customer.getFirstName() + " " + customer.getLastName();
        return new OrderResponse(entity.getId(), totalAmount, fullName, customer.getEmail());
    }

    private List<ProductEntity> getProducts(Map<String, Integer> products) {
        Set<Long> result = new HashSet<>();
        products.keySet().forEach(key -> {
            Long id = Long.parseLong(key.split("_")[0]);
            result.add(id);
        });
        return productService.getProductsByIds(result.stream().toList());
    }

    private OrderEntity createOrderEntity(List<ProductEntity> products,
                                          CustomerEntity customer, double totalAmount) {
        OrderEntity entity = new OrderEntity();
        entity.setProducts(products);
        entity.setTotalAmount(totalAmount);
        entity.setStatus("processing");
        entity.setCustomer(customer);
        return entity;
    }

    private double calculateTotalAmount(List<Cart> cartItems) {
        return cartItems
                .stream()
                .mapToDouble(Cart::getPrice)
                .sum();
    }

    private List<Cart> getCartItemList(Map<String, Integer> products) {
        List<Cart> cartList = new ArrayList<>();
        products.forEach((key, value) -> {
            Long id = Long.parseLong(key.split("_")[0]);
            int size = Integer.parseInt(key.split("_")[1]);
            double price = productService.getProductEntityById(id).getPrice();
            cartList.add(new Cart(id, size, value, price));
        });
        return cartList;
    }
}
