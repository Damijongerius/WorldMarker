package com.dami.worldmarker.Regions.Models;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class BlockInformation {
    Material material;

    BlockFace face;

    public BlockInformation(Material _material, BlockFace _face){
        this.material = _material;
        this.face = _face;
    }

}
