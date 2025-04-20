package uz.nodir.file.model.dto.core.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nodir
 * on Date 08 янв., 2024
 * Java Spring Boot by Davr Coders
 */
@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleResponse<S> {
    private CoreError error;
    private Boolean status;
    private S data;
    private Map<Object, Object> params = new HashMap<>(5);
    private Exception exception;
    private boolean isTimeout = false;

    public SimpleResponse() {
        this.status = true;
    }

    public SimpleResponse(S data) {
        this.status = true;
        this.data = data;
    }

    public SimpleResponse(Map<Object, Object> params, Boolean isSuccess) {
        this.status = isSuccess;
        this.params = params;
    }

    public SimpleResponse(Integer id, String message) {
        this.status = false;
        this.error = new CoreError(id, message);
    }


    public SimpleResponse(Integer id, String message, Exception ex) {
        this.status = false;
        this.error = new CoreError(id, message);
        this.exception = ex;
    }

    public Map<Object, Object> toMap() {
        var entityMap = new HashMap<>();
        entityMap.put("error", this.error);
        entityMap.put("status", this.status);
        entityMap.put("data", this.data);
        entityMap.put("params", this.params);
        return entityMap;
    }
}
