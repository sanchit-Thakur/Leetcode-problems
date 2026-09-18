class Foo {
    private java.util.concurrent.Semaphore sem12 = new java.util.concurrent.Semaphore(0);
    private java.util.concurrent.Semaphore sem23 = new java.util.concurrent.Semaphore(0);

    public Foo() {
        
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        sem12.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        sem12.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        sem23.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        sem23.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}