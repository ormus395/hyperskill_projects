# # Objective
# # 1) Take game state from user, and print it
# # 2) Analyze the game state and print the result
# #  Game not finished: Grid not filled
# #  Draw: No result, grid filled
# # X Wins: 3 X's in a row
# # O Wins: # O's in a row
# # Impossible: Moves are not possible (more X's or O's) or (Each token has a win)

# """
# Algorith

#    user_input = input()
#    grid = turn user_input into an array of arrays

#       [
#          [row]
#          [row]
#          [row]
#       ]
 
#    analyze the game  state array
#       game_not_finished
#       draw
#       win, x, or o
#       impossible
# """

def game():
   game_over = False
   grid = [[' ', ' ', ' '],[' ', ' ', ' '],[' ', ' ', ' ']]
   turn = 0 # 0 = player 1, 1 = player two
   result = 'none'

   # determine row won
   def row_win(grid):
      winner = ''
      for row in grid:
         front_of_row = row[0]
         if front_of_row != " " and row[0] == row[1] and row[0] == row[2]:
               winner = front_of_row
      return winner

   # determine col win
   def col_win(grid):
      winner = ''
      for i in range(3):
         top_of_col = grid[0][i]
         if top_of_col != " " and top_of_col == grid[1][i] and top_of_col == grid[2][i]:
               winner = top_of_col
      return winner

   # determine diag win
   def daig_win(grid):
      winner = ''
      if grid[0][0] != " " and grid[0][0] == grid[1][1] and grid[0][0] == grid[2][2]:
         winner = grid[0][0]

      if grid[0][2] != " " and grid[0][2] == grid[1][1] and grid[0][2] == grid[2][0]:
         winner = grid[0][2]
      
      return winner

   def print_board(grid):
      print("---------")
      for row in grid:
         print("|", end = " ")
         for col in row:
            print(col, end = " ")
         print("|")
      print("---------")

   def find_draw(grid):
      count = 0
      is_draw = False
      for row in grid:
         for col in row:
            if col != " ":
               count += 1
      if count == 9:
         is_draw = True
      return is_draw

   def validate_coords(grid, row, col):
      valid = True
      if row < 1 or row > 3:
         valid = False
         print('Coordinates should be from 1 to 3!')
      elif col < 1 or col > 3:
         valid = False
         print('Coordinates should be from 1 to 3!')
      elif grid[row - 1][col - 1] != "_" and grid[row - 1][col - 1] != " ":
         valid = False
         print('This cell is occupied! Choose another one!')
      return valid

   def get_coords(grid):
      while(True):
         u_input = input("Enter the coordinates: ")
         row = 0
         col = 0
         coords_entered_correctly = True
         u_array = u_input.split(' ')
         if len(u_array) > 2:
            print('Too many arguments')
         else:
            try:
               # coords come in +1 compared to the actual array access
               row = int(u_array[0])
               col = int(u_array[1])

               if not validate_coords(grid, row, col):
                  coords_entered_correctly = False
               else:
                  coords_entered_correctly = True
            except:
               print('You should enter numbers!')
               coords_entered_correctly = False

         if(coords_entered_correctly):
            return [row - 1, col - 1]

   def place_token(coords, turn):
      token = ''
      
      if turn == 0:
         token = "X"
         turn = 1
      else:
         token = "O"
         turn = 0
      
      row = coords[0]
      col = coords[1]

      grid[row][col] = token

      return turn

   print_board(grid)

   while not game_over:
      coords = get_coords(grid)
      turn = place_token(coords, turn)
      print_board(grid)

      if row_win(grid):
         result = row_win(grid)
      elif col_win(grid):
         result = col_win(grid)
      elif daig_win(grid):
         result = daig_win(grid)
      elif find_draw(grid):
         result = 'Draw'

      if result != 'none':
         break

   if result == "Draw":
      print(result)
   else:
      print(result + ' wins')
game()
