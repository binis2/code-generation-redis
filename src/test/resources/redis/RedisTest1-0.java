/*Generated code by Binis' code generator.*/
package net.binis.codegen.redis.test;

import net.binis.codegen.redis.modifier.impl.RedisModifierImpl;
import net.binis.codegen.modifier.Modifiable;
import net.binis.codegen.factory.CodeFactory;
import net.binis.codegen.collection.CodeListImpl;
import net.binis.codegen.collection.CodeList;
import javax.annotation.processing.Generated;
import java.util.List;
import java.io.Serializable;

@Generated(value = "net.binis.codegen.redis.test.RedisTestPrototype", comments = "RedisTest")
public class RedisTestImpl implements RedisTest, Modifiable<RedisTest.Modify>, Serializable {

    // region constants
    public static final String PREFIX = "base:";
    // endregion

    protected String _key;

    protected int data;

    protected List<String> list;

    protected String title;

    // region constructor & initializer
    static {
        CodeFactory.registerType(RedisTest.class, params -> new RedisTestImpl((String) params[0]), null);
    }

    public RedisTestImpl() {
    }

    public RedisTestImpl(String key) {
        _key = key;
    }
    // endregion

    // region getters
    public int getData() {
        return data;
    }

    public List<String> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    public String key() {
        return PREFIX + _key;
    }
    // endregion

    // region setters
    public void setData(int data) {
        this.data = data;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RedisTest.Modify with() {
        return new RedisTestModifyImpl(this);
    }
    // endregion

    // region inner classes
    @Generated("ModifierEnricher")
    @SuppressWarnings("unchecked")
    protected class RedisTestModifyImpl extends RedisModifierImpl<RedisTest.Modify, RedisTest> implements RedisTest.Modify {

        protected RedisTestModifyImpl(RedisTest parent) {
            super(parent);
        }

        public RedisTest.Modify data(int data) {
            RedisTestImpl.this.data = data;
            return this;
        }

        public RedisTest done() {
            return RedisTestImpl.this;
        }

        public CodeList list() {
            if (RedisTestImpl.this.list == null) {
                RedisTestImpl.this.list = new java.util.ArrayList<>();
            }
            return new CodeListImpl<>(this, RedisTestImpl.this.list);
        }

        public RedisTest.Modify list(List<String> list) {
            RedisTestImpl.this.list = list;
            return this;
        }

        public RedisTest.Modify title(String title) {
            RedisTestImpl.this.title = title;
            return this;
        }
    }
    // endregion
}
