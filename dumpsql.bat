@echo off
echo '###################'
echo 'dump database littleserver start...'
mysqldump -h localhost -u littleserver littleserver > mydb.sql
echo 'dump database littleserver over...'
pause