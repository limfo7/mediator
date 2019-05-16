
abstract class Colleague {

    Mediator mediator;

    Colleague(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void displayWindow();
}
