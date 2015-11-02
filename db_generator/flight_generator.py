import random

def establishTable(conn):
    qs = ('drop table flights')
    executeSQL(conn, qs)
    conn.commit()

    qs = (
        '''Create table flights
          (id INT,
           src_id INT,
           dest_id INT,
           departure_date VARCHAR(20),
           departure_time VARCHAR(10),
           price DECIMAL(10,2),
           avail_seats VARCHAR(400),
           bag_price DECIMAL(8,2),
           seat_price DECIMAL(8,2),
           miles INT);'''
    )
    executeSQL(conn, qs)

def generateEntries(conn):
    rows = range(1,20)
    columns = ['A', 'B', 'C', 'D', 'E', 'F']
    months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept',
              'Oct', 'Nov', 'Dec']
    seat_list = []
    for r in rows:
        for c in columns:
            this_seat = c+str(r)
            seat_list.append(this_seat)
    seat_list = ','.join(seat_list)

    qs = ("Select id from airports;")
    qr = executeSQL(conn, qs)
    airport_ids = []
    for row in qr:
        airport_ids.append(row[0])


    for i in range(1,100000):
        bag = float(random.randint(1500, 10000))/100
        seat = float(random.randint(1500,10000))/100
        miles = random.randint(100, 5000)
        src_id = random.choice(airport_ids)
        dest_id = random.choice(airport_ids)
        ddate = (random.choice(months) + ' ' + str(random.randint(1, 31))+ ',' +
                 str(random.randint(2016, 2018)))
        dtime = (str(random.randint(1, 12))+':'+str(random.randint(0, 59))+' '+
                 random.choice(['am', 'pm']))
        price = float(random.randint(10000, 120000))/100
        while dest_id == src_id:
            dest_id = random.choice(airport_ids)
        qs = ('insert into flights '
                '(id, src_id, dest_id, departure_date, departure_time, price, avail_seats, bag_price, seat_price, miles) '
              'values '
                '('+str(i)+', '+str(src_id)+', '+str(dest_id)+', "'+ddate+'", "'+dtime+'", '
                ' '+str(price)+', "'+seat_list+'", '+str(bag)+', '+str(seat)+', '+str(miles)+');')
        executeSQL(conn, qs)
    conn.commit()

    qs = ('select * from flights;')
    executeSQL(conn,qs)

def executeSQL(conn, query_string):
    try:
        cursor = conn.cursor()
        cursor.execute(query_string)
    except Exception as e:
        print "Error: "+str(e)

    return cursor
