package usersdb.util;

import com.google.gson.*;
import usersdb.dto.UserDTO;
import usersdb.model.Role;

import java.lang.reflect.Type;

public class UserDtoSerializer implements JsonSerializer<UserDTO> {
    @Override
    public JsonElement serialize(UserDTO src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject userRolesDataJson = new JsonObject();

        JsonArray jsonRoleList = new JsonArray();

        for (Role currentRole : src.getRoleList()) {
            JsonObject currentJson = new JsonObject();

            currentJson.addProperty("id",currentRole.getId());
            currentJson.addProperty("name",currentRole.getName());
            currentJson.addProperty("viewName",currentRole.getViewName());

            jsonRoleList.add(currentJson);
        }

        userRolesDataJson.add("UserRoleList", jsonRoleList);

        return userRolesDataJson;
    }
}

