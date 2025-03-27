public class Team {
    private Pet[] board;

    public Team() {
        this.board = new Pet[5];
    } 

    public void move(int from, int to) {
        if (from < 0 || from >= 5 || to < 0 || to >= 5) {
            throw new IllegalArgumentException("Invalid move location");
        }
        if (from == to) {
            throw new IllegalArgumentException("Cannot move to the same location");
        }

        this.board[to] = this.board[from];
        this.board[from] = null;
    }

    public boolean addPet(Pet pet, int index) {
        if (index < 0 || index >= 5) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (this.board[index] != null && this.board[index].getId() != pet.getId()) {
            throw new IllegalArgumentException("Index is already occupied");
        }
        if (this.board[index].getId() == pet.getId()) {
            return this.board[index].combine(pet);
        }
        this.board[index] = pet;
        return false;
    }
}