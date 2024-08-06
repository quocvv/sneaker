package menu;

import javaswingdev.GoogleMaterialDesignIcon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ModelMenuItem {

    public ModelMenuItem(GoogleMaterialDesignIcon icon, String menuName, String... subMenu) {
        this.icon = icon;
        this.menuName = menuName;
        this.subMenu = subMenu;
    }

    private GoogleMaterialDesignIcon icon;
    private String menuName;
    private String subMenu[];
}
