package io.github.benas.mark;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class MarkPromptProvider implements PromptProvider {

	@Override
	public AttributedString getPrompt() {
		return new AttributedString("bookmark:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
	}
}
