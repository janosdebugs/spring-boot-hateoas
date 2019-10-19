package zone.refactor.hateoas.entity;

public class PartialLink {
    @SuppressWarnings("WeakerAccess")
    public final String href;

    public PartialLink(String href) {
        this.href = href;
    }

    @SuppressWarnings("WeakerAccess")
    public final Link withRel(String rel) {
        return new Link(
            rel,
            href
        );
    }

    @SuppressWarnings("WeakerAccess")
    public final Link withSelfRel() {
        return withRel("self");
    }

    @SuppressWarnings("WeakerAccess")
    public final Link withUpRel() {
        return withRel("up");
    }

    @SuppressWarnings("WeakerAccess")
    public final Link withNextRel() {
        return withRel("next");
    }

    @SuppressWarnings("WeakerAccess")
    public final Link withPrevRel() {
        return withRel("prev");
    }

    @SuppressWarnings("WeakerAccess")
    public final Link withSearchRel() {
        return withRel("search");
    }
}
