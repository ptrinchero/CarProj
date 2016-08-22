import java.util.List;

public interface Searchable {

    List<Integer> getObject(String propertyName, List<String> value);

    void setSearchable(String propertyName);
}
