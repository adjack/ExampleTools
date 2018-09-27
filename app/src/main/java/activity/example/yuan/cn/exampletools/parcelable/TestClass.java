package activity.example.yuan.cn.exampletools.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 123 on 2018/9/11.
 * 相比较Serializable而言Parcelable 效率更高
 * 因为Parcelable无法将数据进行持久化,因此在将数据保存在磁盘的时候,仍然需要使用后者,
 * 因为前者无法很好的将数据进行持久化【不同的Android版本当中,Parcelable可能会不同】
 */

public class TestClass implements Parcelable {
        private String bookName;
        private String author;
        private int publishDate;

        public TestClass()
        {

        }

        public String getBookName()
        {
            return bookName;
        }

        public void setBookName(String bookName)
        {
            this.bookName = bookName;
        }

        public String getAuthor()
        {
            return author;
        }

        public void setAuthor(String author)
        {
            this.author = author;
        }

        public int getPublishDate()
        {
            return publishDate;
        }

        public void setPublishDate(int publishDate)
        {
            this.publishDate = publishDate;
        }

        @Override
        public int describeContents()
        {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel out, int flags)
        {
            out.writeString(bookName);
            out.writeString(author);
            out.writeInt(publishDate);
        }

        public static final Parcelable.Creator<TestClass> CREATOR = new Creator<TestClass>()
        {
            @Override
            public TestClass[] newArray(int size)
            {
                return new TestClass[size];
            }

            @Override
            public TestClass createFromParcel(Parcel in)
            {
                return new TestClass(in);
            }
        };

        public TestClass(Parcel in)
        {
            //如果元素数据是list类型的时候需要： lits = new ArrayList<?> in.readList(list);
            //否则会出现空指针异常.并且读出和写入的数据类型必须相同.如果不想对部分关键字进行序列化,可以使用transient关键字来修饰以及static修饰.
            bookName = in.readString();
            author = in.readString();
            publishDate = in.readInt();
        }
    }