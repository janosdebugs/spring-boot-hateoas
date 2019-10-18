package at.pasztor.hateoas.entity;

public class PartialLink {
    public final String href;

    public PartialLink(String href) {
        this.href = href;
    }

    public final Link withSelfRel() {
        return new Link(
            "self",
            href
        );
    }

    public final Link withUpRel() {
        return new Link(
            "up",
            href
        );
    }

    public final Link withNextRel() {
        return new Link(
            "next",
            href
        );
    }

    public final Link withPrevRel() {
        return new Link(
            "prev",
            href
        );
    }

    public final Link withSearchRel() {
        return new Link(
            "search",
            href
        );
    }
}
