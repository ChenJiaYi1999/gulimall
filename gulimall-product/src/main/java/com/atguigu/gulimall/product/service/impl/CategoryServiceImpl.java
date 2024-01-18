package com.atguigu.gulimall.product.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        List<CategoryEntity> level1Menus;
        level1Menus = categoryEntities.stream().filter(categoryEntity ->
                categoryEntity.getParentCid() == 0
        ).peek((menu) -> menu.setChildren(getChildren(menu, categoryEntities))).sorted(
            Comparator.comparing(CategoryEntity::getSort, Comparator.nullsFirst(Comparator.naturalOrder()))
        ).collect(Collectors.toList());
        return level1Menus;
    }

    @Override
    public void removeMenuByIds(List<Long> asList) {
        //TODO 检查当前删除的菜单，是否被别的地方引用
//        List<CategoryBrandRelationEntity> categoryBrandRelation =
//                categoryBrandRelationService.list(new QueryWrapper<CategoryBrandRelationEntity>().in("catelog_id", asList));

//        if (categoryBrandRelation.size() == 0) {
            //逻辑删除
            baseMapper.deleteBatchIds(asList);
//        } else {
//            throw new RuntimeException("该菜单下面还有属性，无法删除!");
//        }
    }

    private List<CategoryEntity> getChildren(CategoryEntity category, List<CategoryEntity> all){
        List<CategoryEntity> children;
        children = all.stream().filter(categoryEntity ->
            Objects.equals(categoryEntity.getParentCid(), category.getCatId())
        ).peek((categoryEntity) -> categoryEntity.setChildren(getChildren(categoryEntity, all))).sorted(
            Comparator.comparing(CategoryEntity::getSort, Comparator.nullsFirst(Comparator.naturalOrder()))
        ).collect(Collectors.toList());
        return children;
    }

}
