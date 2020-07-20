package cc.ghast.packet.reflections;

public interface MethodInvoker {
    /**
     * Invoke a method on a specific target object.
     *
     * @param target    - the target object, or NULL for a static method.
     * @param arguments - the arguments to pass to the method.
     * @return The return value, or NULL if is void.
     */
    public Object invoke(Object target, Object... arguments);
}