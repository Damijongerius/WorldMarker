package com.dami.worldmarker.Regions.Models;

//foundation of all regions

import java.util.List;

public interface Region<T> {
    public String getDisplayName();

    public List<T> getRegionInformation();
}
