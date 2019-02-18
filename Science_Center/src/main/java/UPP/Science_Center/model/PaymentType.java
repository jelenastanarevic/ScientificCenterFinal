package UPP.Science_Center.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class PaymentType {

    @NotNull
    @Column(nullable = false, length = 30)
    private String typeName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentTypeId;

    //tipovi placanja za svaki casopis - manyTomany klasa izmodelovana sa entitetom MagazinePaymentType
    @OneToMany(mappedBy = "paymentType", cascade = CascadeType.REMOVE)
    private List<MagazinePaymentType> magazinePaymentTypes;

    public PaymentType() {
    }

    public PaymentType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Long paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }


}
