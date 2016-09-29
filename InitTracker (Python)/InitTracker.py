import datetime
import os
import json
from Tkinter import *

"""To Do:
      turn counter
      status effect countdown
      """

class InitApp(Tk):
   """This is where the GUI elements go"""
   def __init__(self, parent):
      Tk.__init__(self, parent)
      self.parent = parent
      self.rownumber = 0
      self.listoplayers = []
      self.ttick = 0
      self.turncount = 0
      self.startup()


   def newplayer(self, event = '<Return>'):

      player = Player()
      player.name = self.namevar.get()

      self.listoplayers += [player]

      self.newinit(player)



   def newinit(self, player):

      self.name = Frame(self.orderbox)
      self.name.grid(row = self.rownumber, column = 0)
      self.rownumber += 1 

      if player.active == True and self.ttick < 30:
         self.name.config(bg = "green")
      elif player.active == True and 30 < self.ttick < 45:
         self.name.config(bg = "yellow")
      elif player.active == True and 45 < self.ttick:
         self.name.config(bg = "red")

      self.namevar2 = StringVar()
      self.initiativelabel = Label(self.name, textvariable = self.namevar2)
      self.initiativelabel.pack(side = TOP)
      self.namevar2.set(player.name)

      self.initvar = IntVar()
      self.initbox = Entry(self.name, textvariable = self.initvar)
      self.initbox.pack(side = LEFT)
      self.initbox.bind("<Return>", lambda event, arg1 = player, arg2 = self.initvar: self.player_initiative_set(event, arg1, arg2))
      self.initvar.set(u'%d' %(player.init))
      
      self.deletebutton = Button(self.name, text = "X", command = lambda event = "<Return>", arg = player: self.playerpop(event, arg))
      self.deletebutton.pack(side = RIGHT)

      self.statvar = StringVar()
      self.statbox = Entry(self.name, textvariable = self.statvar)
      self.statbox.pack(side = RIGHT)
      self.statbox.bind("<Return>", lambda event, arg1 = player, arg2 = self.statvar: self.player_status_set(event, arg1, arg2)) 
      self.statvar.set(u"%s" %(player.status))

      self.namevar.set(u"")


   def startup(self):
      self.grid()

      self.grid_columnconfigure(0, weight = 1)
      self.grid_columnconfigure(1, weight = 1)
      self.grid_rowconfigure(0, weight = 2)
      self.grid_rowconfigure(1, weight = 0)

      self.orderbox = Frame(self)
      self.orderbox.grid(column = 0, row = 0, sticky = 'nesw')
      self.nextbutton = Button(self, text = "Next Character", command = self.next_player)
      self.nextbutton.grid(column = 0, row = 1, sticky = "ew")

      self.timerbox = Canvas(self, bg = 'white')
      self.timerbox.grid(column = 1, row = 0, rowspan = 1, sticky = 'nesw')

      self.characteradd = Frame(self)
      self.characteradd.grid(column = 0, row = 2, sticky = 'ew')

      self.namevar = StringVar()
      self.entrybox = Entry(self.characteradd, textvariable = self.namevar)
      self.entrybox.pack(side = LEFT, expand = 'true', fill = 'x')
      self.entrybox.bind("<Return>", self.newplayer)

      self.entrybutton = Button(self.characteradd, text = u'Add to Initiative Order', command = self.newplayer)
      self.entrybutton.pack(side = RIGHT)

      self.menubar = Menu(self)
      self.timermenu = Menu(self.menubar, tearoff = 0)
      self.tvar = BooleanVar()
      self.timermenu.add_command(label = "Save", command = self.Initiative_Save)
      self.timermenu.add_command(label = "Load", command = self.Initiative_Load)
      self.timermenu.add_separator()
      self.timermenu.add_checkbutton(label = "Enable Timer", variable = self.tvar, command = self.timer_make)
      self.menubar.add_cascade(label = "Menu", menu = self.timermenu)

   def player_initiative_set(self, event, arg1, arg2):
      for q in self.listoplayers:
         if q.name == arg1.name:
            q.init = arg2.get()
            self.reorder()

   def reorder(self):
      self.rownumber = 0
      self.orderbox.destroy()
      self.orderbox = Frame(self)
      self.orderbox.grid(column = 0, row = 0, sticky = 'nesw')

      self.listoplayers.sort(reverse = True, key = lambda player: int(player.init))

      for p in self.listoplayers:
         self.newinit(p)

   def on_resize(self, event):
      self.timerbox.delete(self.timer)
      self.timey = self.timerbox.winfo_height()- 50
      self.timex = self.timerbox.winfo_width() - 50
      self.timer =  self.timerbox.create_arc( self.timex, self.timey, 50, 50, start = 90, extent = -1, fill = "red")

   def timer_make(self):
      if self.tvar.get() == True:
         self.timey = self.timerbox.winfo_height() - 50
         self.timex = self.timerbox.winfo_width() - 50
         self.timer = self.timerbox.create_arc( self.timex, self.timey, 50, 50, start = 90, extent = -1, fill = "red")
         self.timerbox.bind("<Configure>", self.on_resize)
         self.timer_start()

      else: 
         try:
            self.timerbox.delete(self.timer)
         except:
            pass

   def timer_start(self):
      timeup = (-6 * self.ttick)
      if self.ttick < 30:
         self.timerbox.itemconfig(self.timer, extent = timeup, fill = "green")
      elif 30 <= self.ttick < 45:
         self.timerbox.itemconfig(self.timer, extent = timeup, fill = "yellow")
      else:
         self.timerbox.itemconfig(self.timer, extent = timeup, fill = "red")

      if self.ttick < 59:
         self.ttick += 1

      self.after(500, self.timer_start)

   def playerpop(self, event, player):
      for p in self.listoplayers:
         if p.name == player.name:
            self.listoplayers.remove(p)
            self.reorder()

   def player_status_set(self, event, player, statvar):
      player.status = statvar.get()

   def next_player(self):

      for p in self.listoplayers:
         if p.active == True:
            p.active = False
            try:
               q = self.listoplayers[self.listoplayers.index(p)+1]
            except IndexError:
               q = self.listoplayers[0]
               self.turncount += 1
            q.active = True
            self.reorder()
            self.ttick = 0
            break
      else: 
         self.listoplayers[0].active = True
         self.reorder()
         self.ttick = 0

   def Initiative_Save(self):
      list_to_save = []
      player_stats = []
      for p in self.listoplayers:
         player_stats.append(p.name)
         player_stats.append(p.init)
         player_stats.append(p.dex)
         player_stats.append(p.status)
         player_stats.append(p.active)
         list_to_save.append(player_stats)
         player_stats = []

      with open('InitSave.json', 'w') as fp:
         json.dump(list_to_save, fp, indent = 3) 

   def Initiative_Load(self):
      list_to_load = []
      try:
         with open('InitSave.json', 'r') as fp:
            list_to_load = json.load(fp)

         for p in list_to_load:
            player = Player()
            player.name = p[0]
            player.init = p[1]
            player.dex = p[2]
            player.status = p[3]
            player.active = p[4]
            self.listoplayers.append(player)
      except ValueError:
         print "Nothing to Load"
         

      self.reorder()







class Player():
   """Contains info needed for initiative order"""
   def __init__(self):
      Player.name = str
      Player.init = 0
      Player.dex = 0 #initiative modifier
      Player.status = "Status Effect(s)" #Status effect
      Player.active = False





      


if __name__ == '__main__':
   app = InitApp(None)
   app.title("Initiative Tracker")
   app.config(menu = app.menubar)
   app.mainloop()



