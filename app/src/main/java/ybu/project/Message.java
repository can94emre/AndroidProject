package ybu.project;

class Message {
    int id;
    String subject;
    String content;
    Message(String id, String subject, String content) {
        this.id = Integer.parseInt(id);
        this.subject = subject;
        this.content = content;
    }
}
