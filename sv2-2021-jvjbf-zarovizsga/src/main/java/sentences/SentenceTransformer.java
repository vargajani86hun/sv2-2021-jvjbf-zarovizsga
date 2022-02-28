package sentences;

public class SentenceTransformer {
    public String shortenSentence(String sentence) {
        validateSentence(sentence);
        String[] words = sentence.split(" ");
        if (words.length < 5) {
            return sentence;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(words[0]);
        sb.append(" ... ");
        sb.append(words[words.length - 1]);
        return sb.toString();
    }

    private void validateSentence(String text) {
        final String endSigns = ".!?";
        char firstChar = text.charAt(0);
        char lastChar = text.charAt(text.length() - 1);
        if (firstChar != Character.toUpperCase(firstChar)) {
            throw new IllegalArgumentException("Must start with capital letter!");
        } else if (endSigns.indexOf(lastChar) < 0){
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }
}
