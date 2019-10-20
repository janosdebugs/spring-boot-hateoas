package zone.refactor.hateoas.contract;

public interface PartialLink {
    Link withRel(String rel);
    default Link withSelfRel() {
        return withRel("self");
    }
    default Link withUpRel() {
        return withRel("up");
    }
    default Link withNextRel() {
        return withRel("next");
    }
    default Link withPrevRel() {
        return withRel("prev");
    }
    default Link withSearchRel() {
        return withRel("search");
    }
}
