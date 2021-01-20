package ndn.livestream.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
@Entity(name = "category")
public class CategoryEntity extends BaseEntity {
    private String name;
    private String image;
    private String imageBanner;
    private String description;
}
