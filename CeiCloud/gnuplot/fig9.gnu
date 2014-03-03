set xlabel ""
set ylabel "average packing cost"
set boxwidth 0.9 relative
set style data histograms
set style histogram errorbars gap 2 lw 2


set style fill pattern 1 border lt -1
set title ""
set yrange[10000:15000]
plot for [COL=2:4:2] 'data/fig9.dat' using COL:COL+1:xticlabels(1) title columnhead(COL)
set terminal pdf
set output "fig9.pdf"
replot
