package cc.ghast.packet.nms.payload;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public class MinecraftKey implements Comparable<MinecraftKey> {
    protected final String a;
    protected final String b;

    protected MinecraftKey(String[] var0) {
        this.a = StringUtils.isEmpty(var0[0]) ? "minecraft" : var0[0];
        this.b = var0[1];
        if (!this.a.chars().allMatch((var0x) -> {
            return var0x == 95 || var0x == 45 || var0x >= 97 && var0x <= 122 || var0x >= 48 && var0x <= 57 || var0x == 46;
        })) {
            throw new ArtemisDigestException("Non [a-z0-9_.-] character in namespace of location: " + this.a + ':' + this.b);
        } else if (!this.b.chars().allMatch((var0x) -> {
            return var0x == 95 || var0x == 45 || var0x >= 97 && var0x <= 122 || var0x >= 48 && var0x <= 57 || var0x == 47 || var0x == 46;
        })) {
            throw new ArtemisDigestException("Non [a-z0-9/._-] character in path of location: " + this.a + ':' + this.b);
        }
    }

    public MinecraftKey(String var0) {
        this(b(var0, ':'));
    }

    public MinecraftKey(String var0, String var1) {
        this(new String[]{var0, var1});
    }

    public static MinecraftKey a(String var0, char var1) {
        return new MinecraftKey(b(var0, var1));
    }

    @Nullable
    public static MinecraftKey a(String var0) {
        try {
            return new MinecraftKey(var0);
        } catch (ArtemisDigestException var2) {
            return null;
        }
    }

    protected static String[] b(String var0, char var1) {
        String[] var2 = new String[]{"minecraft", var0};
        int var3 = var0.indexOf(var1);
        if (var3 >= 0) {
            var2[1] = var0.substring(var3 + 1, var0.length());
            if (var3 >= 1) {
                var2[0] = var0.substring(0, var3);
            }
        }

        return var2;
    }

    public String getKey() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public String toString() {
        return this.a + ':' + this.b;
    }

    public boolean equals(Object var0) {
        if (this == var0) {
            return true;
        } else if (!(var0 instanceof MinecraftKey)) {
            return false;
        } else {
            MinecraftKey var1 = (MinecraftKey)var0;
            return this.a.equals(var1.a) && this.b.equals(var1.b);
        }
    }

    public int hashCode() {
        return 31 * this.a.hashCode() + this.b.hashCode();
    }

    public int compareTo(MinecraftKey var0) {
        int var1 = this.b.compareTo(var0.b);
        if (var1 == 0) {
            var1 = this.a.compareTo(var0.a);
        }

        return var1;
    }

    public static MinecraftKey a(StringReader var0) throws ArtemisDigestException {
        int var1 = var0.getCursor();

        while(var0.canRead() && a(var0.peek())) {
            var0.skip();
        }

        String var2 = var0.getString().substring(var1, var0.getCursor());

        try {
            return new MinecraftKey(var2);
        } catch (ArtemisDigestException var4) {
            var0.setCursor(var1);
            throw new ArtemisDigestException(var4);
        }
    }

    public static boolean a(char var0) {
        return var0 >= '0' && var0 <= '9' || var0 >= 'a' && var0 <= 'z' || var0 == '_' || var0 == ':' || var0 == '/' || var0 == '.' || var0 == '-';
    }

    public static class a implements JsonDeserializer<MinecraftKey>, JsonSerializer<MinecraftKey> {
        public a() {
        }

        public MinecraftKey deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
            return new MinecraftKey(ChatDeserializer.a(var0, "location"));
        }

        public JsonElement serialize(MinecraftKey var0, Type var1, JsonSerializationContext var2) {
            return new JsonPrimitive(var0.toString());
        }
    }
}
