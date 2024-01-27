interface IComponent {
    fun operation()
}

class ConcreteComponent: IComponent {
    override fun operation() {

    }
}

abstract class Decorator(val wrappee: IComponent): IComponent {

    override fun operation() {
        wrappee.operation()
    }

}

class ComponentDecorator1(component: IComponent): Decorator(component) {
    override fun operation() {
        super.operation()
        extraOperation()
    }

    fun extraOperation()
        = println("Component Decoration 1, extraDecoration()")
}

class Componentdecorator2(component: IComponent): Decorator(component) {
    override fun operation() {
        super.operation()
        extraOperation()
    }

    fun extraOperation()
            = println("Component Decoration 2, extraDecoration()")

}

fun main() {
    val obj = ConcreteComponent()

//    val deco1 = ComponentDecorator1(obj)
//    deco1.operation()
//
//    val deco2 = Componentdecorator2(obj)
//    deco2.operation()

    val deco3 = ComponentDecorator1(Componentdecorator2(obj))
    deco3.operation()
}