package zone.refactor.spring.hateoas.entity;

public class PartialLink implements zone.refactor.spring.hateoas.contract.PartialLink {
    @SuppressWarnings("WeakerAccess")
    public final String href;

    public PartialLink(String href) {
        this.href = href;
    }

    @Override
    public zone.refactor.spring.hateoas.contract.PartialLink withHref(final String href) {
        return new PartialLink(href);
    }

    @Override
    public String getHref() {
        return href;
    }

    @SuppressWarnings("WeakerAccess")
    public final Link withRel(String rel) {
        return new Link(
            rel,
            href
        );
    }
}
