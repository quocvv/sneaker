package card;

import java.awt.Color;
import javaswingdev.GoogleMaterialDesignIcon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ModelCard {

    private GoogleMaterialDesignIcon icon;
    private Color color1;
    private Color color2;
    private String values;
    private String description;
}
