package com.izejs.simple.service;


import com.baomidou.mybatisplus.extension.api.R;

import java.util.HashMap;

public interface MenuService {
    R selectMenuList(HashMap<String, Object> param);

    R selectMainMenu(HashMap<String, Object> param);
}
