package com.wyz.coffee.lang;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 * 根据不同条件指派不同处理类的动态处理器
 *
 * @param <Param>  参数的泛型
 * @param <Result> 结果的泛型
 */
public abstract class DynamicHandler<Param, Result> {
    /**
     * 使用hashMap,这里不需要考虑线程安全
     */
    protected final Map<String, Function<Param, Result>> map = new HashMap<>();

    /**
     * 通过方法参数找出key的类,必须实现
     * 返回的是一个key的list
     *
     * @param param
     * @return
     */
    protected abstract List<String> getKeys(Param param);

    /**
     * 指定key,新增handler
     *
     * @param key
     * @param handler
     */
    public void add(String key, Function<Param, Result> handler) {
        map.put(key, handler);
    }

    /**
     * 通过param解析出来的keyList,按照顺序寻找指定的handler
     * 只会执行找到的第一个handler
     *
     * @param param
     * @return 没有找到处理器的异常
     * @e
     */
    public Result apply(Param param, RuntimeException noHandler) {
        Optional<Function<Param, Result>> op = this.getKeys(param)
                .stream()
                .map(key -> map.get(key))
                .filter(function -> function != null)
                .findFirst();
        Function<Param, Result> function;
        if (noHandler == null) {
            function = op.get();
        } else {
            function = op.orElseThrow(() -> noHandler);
        }
        return function.apply(param);
    }

    public Result apply(Param param) {
        return apply(param, null);
    }
}
