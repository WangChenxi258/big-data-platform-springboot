package com.buct.bigDataPlatform.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: BigDataPlatform_spring_boot
 * @author: CarLor
 * @create: 2024-06-12 16:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean <T> {
    private Long Total;
    private List<T> items;
}