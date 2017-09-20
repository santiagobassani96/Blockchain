package Model;

/**
 * This class represents the nodes of a {@code Model.Blockchain}
 * @param <T> The parameter is a generic for the type of the stored data.
 */
public class Block <T>{

    private long index;
    private T data;
    private long nounce;
    private String prevHash;
    private String hash;
    private String zeros;

    /**
     *Creates a {@code Model.Block} object for a {@code Model.Blockchain}.
     * @param index index of this node on the Model.Blockchain.
     * @param data information to store in this Model.Block.
     * @param prevHash the hash encoded in SHA-256 of the previous Model.Block.
     */
    public Block(long index, T data, String prevHash, String zeros){
        if(index < 0 ) throw new IllegalArgumentException("index were incorrect");
        if(prevHash == null ) throw new IllegalArgumentException("previous hash were incorrect");
        if(zeros == null) throw new IllegalArgumentException("zeros were incorrect");
        this.data = data;
        this.index = index;
        this.prevHash = prevHash;
        this.zeros = zeros;
        nounce = 0;
        hash = HashFunction.getSingletonInstance().encode(Long.toString(index) + data.toString() + prevHash);   //checkear como se hace
    }

    /**
     * This method figured out the nounce number that valid the hash of this Model.Block.
     */
    public void mine() {
        nounce = 0;
        String word = Long.toString(index) + data.toString() + prevHash;
        hash = HashFunction.getSingletonInstance().encode(word + Long.toString(nounce));
        while(!hash.matches(this.zeros)){
            nounce++;
            hash = HashFunction.getSingletonInstance().encode(word + Long.toString(nounce));
        }
    }

    /**
     *This method validate the hash of the {@code Model.Block} object.
     * @return true if the hash of this block begins with the specified number of zeros, false otherwise.
     */
    public boolean isValidHash() {
        String word = Long.toString(index) + data.toString() + prevHash;
        hash = HashFunction.getSingletonInstance().encode(word + Long.toString(nounce));
        return hash.matches(this.zeros);
    }

    /**
     *This method return the index of the {@code Model.Block}.
     * @return a copy of the {@code Model.Block} index in a {@code long}  integer.
     */
    public long getIndex(){
        return index;
    }

    /**
     * This method return the hash of the {@code Model.Block}.
     * @return a {@code String} with the hash of the block expressed in hexadecimal.
     */
    public String getHash(){
        return hash;
    }

    /**
     *This method return the data of the {@code Model.Block}.
     * @return a T object with the stored data in the {@code Model.Block}.
     */
    public T getData(){
        return data;
    }

    /**
     *This method return the hash of the previous {@code Model.Block}.
     * @return a {@code String} with the hash of the previous {@code Model.Block}.
     */
    public String getPrevHash(){
        return prevHash;
    }

}