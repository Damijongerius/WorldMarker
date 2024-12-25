package com.dami.worldmarker.Regions.Models.Tree;

import com.dami.worldmarker.Regions.Models.Region;
import com.dami.worldmarker.Saving.Savable;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TreeRegion extends Savable implements Region<Vector> {

    public static TreeDefinitions definitions = TreeDefinitions.getInstance();

    private UUID referenceId;

    private List<Vector> locations;

    private String displayName;

    private UUID parent;

    public TreeRegion(UUID referenceId, List<Vector> locations, String displayName, UUID parent){
        this.referenceId = referenceId;
        this.locations = locations;
        this.displayName = displayName;
        this.parent = parent;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public List<Vector> getRegionInformation() {
        return locations;
    }

    public UUID getParent() {
        return parent;
    }

    public UUID getReferenceId() {
        return referenceId;
    }

    @Override
    public Map<String, Object> saveToYaml() {
        Map<String,Object> map = new HashMap<>();

        map.put("referenceId", referenceId.toString());
        map.put("displayName", displayName);
        map.put("parent", parent.toString());

        Map<String,Object> locationMap = new HashMap<>();
        for (int i = 0; i < locations.size(); i++) {
            Map<String,String> vectorMap = new HashMap<>();
            vectorMap.put("x", String.valueOf(locations.get(i).getX()));
            vectorMap.put("y", String.valueOf(locations.get(i).getY()));
            vectorMap.put("z", String.valueOf(locations.get(i).getZ()));

            locationMap.put(String.valueOf(i), vectorMap);
        }

        map.put("locations", locationMap);

        return map;
    }

    @Override
    public void loadFromConfig(ConfigurationSection config) {

        referenceId = UUID.fromString(config.getString("referenceId"));
        displayName = config.getString("displayName");
        parent = UUID.fromString(config.getString("parent"));

        ConfigurationSection locationSection = config.getConfigurationSection("locations");
        if (locationSection != null) {
            for (String key : locationSection.getKeys(false)) {
                ConfigurationSection vectorSection = locationSection.getConfigurationSection(key);
                if (vectorSection != null) {
                    locations.add(new Vector(vectorSection.getDouble("x"), vectorSection.getDouble("y"), vectorSection.getDouble("z")));
                }
            }
        }
    }

    @Override
    public void loadBaseConfig() {

    }
}
