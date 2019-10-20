package zone.refactor.spring.hateoas.contract;

/**
 * The link provider reads the configuration for entity-endpoint mappings and returns partial link objects for embedding.
 */
public interface LinkProvider {
    /**
     * Return a link to a list of resources.
     *
     * @param entityClass the class of the entity in question.
     * @param pathParameters the parameters that are required to construct the URL.
     * @param <T> The entity type.
     * @return a partial link, missing the relation attribute.
     */
    <T extends LinkedEntity<?>> PartialLink getResourceListLink(Class<T> entityClass, String... pathParameters);

    /**
     * Return a link to a single resource.
     *
     * @param entityClass the class of the entity in question.
     * @param id the identifier of the entity in question. The URL must end with the ID as a parameter.
     * @param pathParameters the parameters that are required to construct the URL.
     * @param <T> The entity type.
     * @return a partial link, missing the relation attribute.
     */
    <T extends LinkedEntity<?>> PartialLink getResourceLink(Class<T> entityClass, String id, String... pathParameters);
}
