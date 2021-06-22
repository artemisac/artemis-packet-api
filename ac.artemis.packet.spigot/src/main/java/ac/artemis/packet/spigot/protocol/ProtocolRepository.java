package ac.artemis.packet.spigot.protocol;

import ac.artemis.packet.protocol.ProtocolVersion;
import ac.artemis.packet.protocol.format.WrittenEnumProtocol;
import ac.artemis.packet.spigot.ArtemisSpigotPlugin;
import ac.artemis.packet.spigot.serialization.PacketSerializer;
import ac.artemis.packet.spigot.utils.Accessor;
import ac.artemis.packet.spigot.utils.ServerUtil;
import ac.artemis.packet.wrapper.PacketClass;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ProtocolRepository extends Accessor {
    private final Map<ProtocolVersion, WrittenEnumProtocol> protocolMap = new HashMap<>();

    public ProtocolRepository(ArtemisSpigotPlugin plugin) {
        super(plugin);
    }

    @Override
    public void create() {
        final Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(PacketClass.class, new PacketSerializer())
                .create();

        for (ProtocolVersion value : ProtocolVersion.values()) {
            final InputStream inputStream = plugin.getResource(value.getServerVersion() + ".json");

            if (inputStream == null)
                continue;

            final JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            final WrittenEnumProtocol writtenEnumProtocol = gson.fromJson(jsonReader, WrittenEnumProtocol.class);

            protocolMap.put(value, writtenEnumProtocol);
            ServerUtil.sendConsoleMessage("&aAdded &rprotocol of version &b&l" + value);
        }
    }

    @Override
    public void dispose() {

    }
}
