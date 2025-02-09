import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BookManager {
    public ArrayList<Book> books = new ArrayList<>();
    public List<User> users;

    public BookManager(List<User> users) {
        this.users = users;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    // 책 추가
    public void addBook(Book book) {
        books.add(book);
    }

    // 도서 제거
    public void removeBook(int bookID) {
        books.removeIf(book -> book.bookID == bookID);
        System.out.println("책 아이디 " + bookID + " 가 제거되었습니다. ");
    }

    // 비동기로 도서 목록을 가져오는 메서드
    public CompletableFuture<List<Book>> getBookListAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // 실제로는 여기서 데이터베이스나 외부 저장소에서 데이터를 가져올 수 있음
                Thread.sleep(1000); // 데이터를 가져오는데 시간이 걸린다고 가정
                return new ArrayList<>(books);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // 비동기로 도서 목록을 출력하는 메서드
    public void displayBooksAsync() {
        System.out.println("도서 목록을 가져오는 중입니다...");

        getBookListAsync()
                .thenAccept(bookList -> {
                    System.out.println("\n=== 도서 목록 ===");
                    for (Book book : bookList) {
                        book.getDetails();
                        System.out.println();
                    }
                    System.out.println("=== 목록 끝 ===");
                })
                .exceptionally(throwable -> {
                    System.out.println("도서 목록을 가져오는 중 오류가 발생했습니다: " + throwable.getMessage());
                    return null;
                });
    }

    // 비동기로 도서 목록을 가져와서 결과를 기다리는 메서드
    public void displayBooksAsyncWithWait() {
        System.out.println("도서 목록을 가져오는 중입니다...");

        try {
            List<Book> bookList = getBookListAsync().get(); // 결과를 기다림
            System.out.println("\n=== 도서 목록 ===");
            for (Book book : bookList) {
                book.getDetails();
                System.out.println();
            }
            System.out.println("=== 목록 끝 ===\n");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("도서 목록을 가져오는 중 오류가 발생했습니다: " + e.getMessage());
        }
    }
}
