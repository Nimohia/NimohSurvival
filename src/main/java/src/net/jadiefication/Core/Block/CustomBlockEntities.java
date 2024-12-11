package src.net.jadiefication.Core.Block;

import org.bukkit.Location;

public enum CustomBlockEntities {

    BAMBOO_FLOWER_CART {
        @Override
        public void createBlockEntity(Location location) {

        }
    };

    public abstract void createBlockEntity(Location location);
}
