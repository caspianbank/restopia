package az.restopia.order.domain.entity;

import az.neotech.commons.audit.DetailedAudit;
import az.restopia.commons.domain.constants.ColumnLengthConstants;
import az.restopia.commons.domain.enums.DeleteStatus;
import az.restopia.order.domain.enums.OrderSource;
import az.restopia.order.domain.enums.OrderStatus;
import az.restopia.order.domain.enums.OrderType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tenant_customer_orders")
public class Order extends DetailedAudit {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "tenant_code", nullable = false, length = ColumnLengthConstants.TENANT_CODE_LEN)
    private String tenantCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderSource source;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "table_number")
    private String tableNumber;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_note")
    private String customerNote;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(name = "description")
    private String description;

    private DeleteStatus deleteStatus;
}
