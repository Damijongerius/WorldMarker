package com.dami.worldmarker.Regions.Models.Tree;

import com.dami.worldmarker.Saving.Savable;
import com.dami.worldmarker.Saving.Serializable;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

@Serializable
public class TreeDefinitions extends Savable {

    //singleton
    private static TreeDefinitions instance;

    //key is the child, value is the parent
    //this is a tree structure
    //if the value is empty, then the key is the root
    private final Map<String,String> definitions = new HashMap<String,String>();

    public void addDefinition(String key, String value) {
        if(value.isEmpty() || definitions.containsKey(value)){
            definitions.put(key, value);
        }
    }

    public void removeDefinition(String key) {
        if (!definitions.containsKey(key)) {
            return;
        }

        definitions.remove(key);
    }

    public void fixedRemoveDefinition(String key) {
        if (!definitions.containsKey(key)) {
            return;
        }
        //gets the value of the key we are removing
        String value = definitions.get(key);
        definitions.remove(key);

        //replacing all the keys that implement the key we are removing with the value of the key we are removing
        for (Map.Entry<String, String> entry : definitions.entrySet()) {
            if (entry.getValue().equals(key)) {
                entry.setValue(value);
            }
        }
    }

    public Map<String, String> getDefinitions() {
        return definitions;
    }

    public String GetDefinitionParent(String key) {
        return definitions.get(key);
    }

    public String[] GetDefinitionTreeParents(String key) {
        String parent = definitions.get(key);
        if (parent == null) {
            //key is the root
            return new String[0];
        }

        String[] parents = GetDefinitionTreeParents(parent);
        String[] newParents = new String[parents.length + 1];
        newParents[0] = parent;
        System.arraycopy(parents, 0, newParents, 1, parents.length);

        return newParents;
    }

    //change this function to be like this structure
    // main weight 0
    // - continent, wild land weight 1
    // -- forest, desert weight 2
    // --- oak, birch weight 3
    public String[][] GetDefinitionTree() {
        String[][] tree = new String[definitions.size()][];
        int i = 0;
        for (Map.Entry<String, String> entry : definitions.entrySet()) {
            tree[i] = GetDefinitionTreeParents(entry.getKey());
            i++;
        }

        return tree;
    }


    @Override
    public Map<String, Object> saveToYaml() {
        Map<String, Object> map = new HashMap<>();

        map.put("definitions", definitions);

        return map;
    }

    @Override
    public void loadFromConfig(ConfigurationSection config) {
        Map<String, String> loadedDefinitions = (Map<String, String>) config.get("definitions");
        if (loadedDefinitions != null) {
            definitions.putAll(loadedDefinitions);
        }
    }

    @Override
    public void loadBaseConfig() {

    }

    public static TreeDefinitions getInstance() {
        if (instance == null) {
            instance = TreeDefinitions.loadFromFile(TreeDefinitions.class, "treeDefinitions");
        }

        return instance;
    }
}
