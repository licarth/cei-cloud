set xlabel "number of cuts D"
set ylabel "approximation ratio"
set boxwidth 0.9 relative
set style data histograms
set style histogram errorbars gap 2 lw 2


set style fill pattern 1 border lt -1
set title ""
set yrange[1.0:1.4]
plot for [COL=2:4:2] 'data/fig10.dat' using COL:COL+1:xticlabels(1) title columnhead(COL)
set terminal pdf
set output "fig10.pdf"
replot
