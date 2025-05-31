package az.neotech.neoeats.layout.entity;


import az.neotech.commons.audit.DetailedAudit;
import az.neotech.neoeats.layout.enums.TableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "restaurant_table")
public class RestaurantTable extends DetailedAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private TableStatus status = TableStatus.AVAILABLE;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;
    private int posX, posY;
}
