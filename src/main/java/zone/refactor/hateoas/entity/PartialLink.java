package zone.refactor.hateoas.entity;

public class PartialLink implements zone.refactor.hateoas.contract.PartialLink {
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
}
