package zone.refactor.spring.hateoas.contract;

/**
 * A partially complete link is a link without it's `rel=` attribute set. Once the `rel=` is set it becomes a
 * {@see zone.refactor.spring.hateoas.contract.Link} entity. This is useful to split the responsibility of setting the
 * `rel=` attribute from actually generating the link.
 */
public interface PartialLink {
    /**
     * This method lets you create a copy of the current object with a modified `href=`. This is useful for modifying
     * the original link obtained from {@see #getHref}.
     *
     * @param href the modified `href=` attribute
     *
     * @return a modified copy of this partial link
     */
    @SuppressWarnings("unused")
    PartialLink withHref(String href);

    /**
     * @return the URL embedded in this link.
     */
    @SuppressWarnings("unused")
    String getHref();

    /**
     * Create a full link by specifying its `rel=`.
     *
     * @param rel the relationship of the current object to the resource on the specified `href=`
     *
     * @return a fully completed link.
     */
    Link withRel(String rel);

    /**
     * @return a full link with `rel="self"`.
     */
    default Link withSelfRel() {
        return withRel("self");
    }
    /**
     * @return a full link with `rel="up"`.
     */
    default Link withUpRel() {
        return withRel("up");
    }
    /**
     * @return a full link with `rel="next"`.
     */
    @SuppressWarnings("unused")
    default Link withNextRel() {
        return withRel("next");
    }
    /**
     * @return a full link with `rel="prev"`.
     */
    @SuppressWarnings("unused")
    default Link withPrevRel() {
        return withRel("prev");
    }
    /**
     * @return a full link with `rel="search"`.
     */
    @SuppressWarnings("unused")
    default Link withSearchRel() {
        return withRel("search");
    }
}
