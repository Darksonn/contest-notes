fimoj.pdf: fimoj.tex */*java
	xelatex -halt-on-error -shell-escape fimoj.tex
