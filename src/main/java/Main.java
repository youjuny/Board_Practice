import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Article> articles = new ArrayList<>();





    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lastArticleId = 1;

        while (true) {
            System.out.print("메뉴 입력 : ");
            String command = sc.nextLine();
            if (command.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (command.equals("add")) {
                System.out.print("게시물 제목을 입력해주세요 : ");
                String title = sc.nextLine();
                System.out.print("게시물 내용을 입력해주세요 : ");
                String content = sc.nextLine();

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm:ss");
                String formatedNow = now.format(formatter);

                Article article = new Article(lastArticleId, title, content, formatedNow);

                articles.add(article);
                lastArticleId++;

                System.out.println("게시물이 등록되었습니다.");

            } else if (command.equals("list")) {
                System.out.println("===================");

                for (int i = 0; i < articles.size(); i++) {
                    Article article = articles.get(i);

                    System.out.printf("번호 : %d\n", article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.println("===================");
                }

            } else if (command.equals("update")) {
                System.out.printf("수정할 게시판 번호 : ");
                int targetId = getParamInt(sc.nextLine(), -1);
                if (targetId == -1) {
                    continue;
                }


                Article article = findById(targetId);

                if (article == null) {
                    System.out.println("없는 게시물입니다.");

                } else {
                    System.out.print("제목 : ");
                    String newTitle = sc.nextLine();
                    System.out.print("내용 : ");
                    String newContent = sc.nextLine();

                    article.setTitle(newTitle);
                    article.setContent(newContent);

                    System.out.println("수정이 완료되었습니다.");
                }


            } else if (command.equals("delete")) {
                System.out.print("삭제할 게시판 번호 : ");
                int targetId = getParamInt(sc.nextLine(), -1);
                if (targetId == -1) {
                    continue;
                }

                Article article = findById(targetId);

                if (article == null) {
                    System.out.println("없는 게시물입니다.");

                } else {
                    articles.remove(article);
                    System.out.printf("%d번 게시물이 삭제되었습니다\n", targetId);
                }
            } else if (command.equals("detail")) {
                System.out.print("상세보기할 번호 : ");
                int targetId = getParamInt(sc.nextLine(), -1);
                if (targetId == -1) {
                    continue;
                }

                Article article = findById(targetId);

                if (article == null) {
                    System.out.println("없는 게시물입니다.");

                } else {
                    System.out.println("===================");
                    System.out.printf("번호 : %d\n", article.getId());
                    System.out.printf("제목 : %s\n", article.getTitle());
                    System.out.printf("내용 : %s\n", article.getContent());
                    System.out.printf("등록날짜 : %s\n", article.getRegDate());
                }
            }
        }
    }

    public static Article findById(int id) {

        Article target = null;

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);

            if (id == article.getId()) {
                target = article;
            }
        }
        return target;
    }

    public static int getParamInt(String input, int defaultValue) {
        try {
            int num = Integer.parseInt(input);
            return num;

        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력하세요");
        }
        return defaultValue;
    }
}
