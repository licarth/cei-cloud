set xlabel "item distribution"
set ylabel "approximation ratio"
set boxwidth 0.9 relative
set style data histograms
set style histogram errorbars gap 2 lw 2

#$$$$$$$$$$$$ ON-LINE
set style fill pattern 1 border lt -1
set title ""
set yrange[1.0:1.40]
plot for [COL=2:4:2] 'data/out.dat' using COL:COL+1:xticlabels(1) title columnhead(COL)
set terminal pdf
set output "gnuplot.pdf"
replot
