package com.hotel.models.bean;

import java.util.HashMap;
import java.util.Map;

public class ExploreBeanProxy {


    public static Map<String,String> getExploreParams(){
        Map<String,String> map = new HashMap<>();
        map.put("_format","for_explore_search_native");
        map.put("cdn_experiments%5B%5D","CHINA_ANDROID_NEW_POI_FILTER");
        map.put("cdn_experiments%5B%5D","MOBILE_FILTER_NEW_DESIGN");
        map.put("children","0");
        map.put("infants","0");
        map.put("is_guided_search","false");
        map.put("items_offset","0");
        map.put("items_per_grid","8");
        map.put("refinement_paths%5B%5D","%2Ffor_you");
        map.put("screen_size","medium");
        map.put("search_by_map","false");
        map.put("show_groupings","true");
        map.put("timezone","Asia%2FShanghai");
        map.put("version","1.7.5");
        map.put("client_id","3092nxybyb0otqw18e8nh5nty");
        map.put("locale","zh-CN");
        map.put("currency","CNY");
        return map;
    }


    public static Map<String,String> getExploreSearchParams(){
        Map<String,String> map = new HashMap<>();

        return map;
    }

}
