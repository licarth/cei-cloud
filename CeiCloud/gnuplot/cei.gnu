set xlabel "item distribution"
set ylabel "precision"
set boxwidth 0.9 relative
set style data histograms
set style histogram errorbars gap 2 lw 2

#$$$$$$$$$$$$ ON-LINE
set style fill pattern 1 border lt -1
set title "on-line for 3 bins and monotone cost"
set yrange[1.0:1.40]
plot for [COL=2:4:2] 'on-line-3bin-monotone-ver2.dat' using COL:COL+1:xticlabels(1) title columnhead(COL)
set terminal pdf
set output "on-line-3bins-monotone-ver2.pdf"
replot
#------------------------------------
set style fill pattern 1 border lt -1
set title "on-line for 10 bins and monotone cost"
set yrange[1.1:1.40]
plot for [COL=2:4:2] 'on-line-10bin-monotone-ver2.dat' using COL:COL+1:xticlabels(1) title columnhead(COL)
set terminal pdf
set output "on-line-10bins-monotone-ver2.pdf"
replot


#$$$$$$$$$$$$ OFF-LINE
set style fill pattern 4 border lt -1
set title "off-line, CIFFD, and monotone cost"
set yrange[1.10:1.25]
plot for [COL=2:4:2] 'off-line-CIFFD-3bin10bins-monotone-ver2.dat' using COL:COL+1:xticlabels(1) title columnhead(COL)
set terminal pdf
set output "off-line-CIFFD-monotone-ver2.pdf"
replot
#----------------------------
set style fill pattern 1 border lt -1
set title "off-line, CDNFL, and monotone cost"
set yrange[1.0:1.65]
plot for [COL=2:4:2] 'off-line-CDNFL-3bin10bins-monotone-ver2.dat' using COL:COL+1:xticlabels(1) title columnhead(COL)
set terminal pdf
set output "off-line-CDNFL-monotone-ver2.pdf"
replot