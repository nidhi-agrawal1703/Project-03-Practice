package in.co.rays.proj3.dto;

public class LanguageTranslationDTO extends BaseDTO {
	
	private String sourceLanguage;
	private String targetLanguage;
	private String inputText;
	private String translatedText;
	
	public String getSourceLanguage() {
		return sourceLanguage;
	}
	public void setSourceLanguage(String sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}
	public String getTargetLanguage() {
		return targetLanguage;
	}
	public void setTargetLanguage(String targetLanguage) {
		this.targetLanguage = targetLanguage;
	}
	public String getInputText() {
		return inputText;
	}
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
	public String getTranslatedText() {
		return translatedText;
	}
	public void setTranslatedText(String translatedText) {
		this.translatedText = translatedText;
	}
	@Override
	public int compareTo(BaseDTO o) {
		return 0;
	}
	@Override
	public String getKey() {
		return id+"";
	}
	@Override
	public String getValue() {
		return sourceLanguage;
	}
	
	
}
