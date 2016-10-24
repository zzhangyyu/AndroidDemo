package com.cslc.demo.activity;

import java.net.HttpURLConnection;
import java.net.URL;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.cslc.demo.R;

/**
 * listView异步加载图片
 * 
 * @author zhangyu
 *
 */
public class LoadImagActivity extends BaseActivity {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = (ListView) findViewById(R.id.list_view);
		ImageAdapter adapter = new ImageAdapter(this, 0, Images.imageUrls);
		listView.setAdapter(adapter);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_load_imag;
	}

	public class ImageAdapter extends ArrayAdapter<String> {

		/**
		 * 图片缓存技术的核心类，用于缓存所有下载好的图片，在程序内存达到设定值时会将最少最近使用的图片移除掉。
		 */
		private LruCache<String, BitmapDrawable> mMemoryCache;

		public ImageAdapter(Context context, int resource, String[] objects) {
			super(context, resource, objects);
			// 获取应用程序最大可用内存
			int maxMemory = (int) Runtime.getRuntime().maxMemory();
			int cacheSize = maxMemory / 8;
			mMemoryCache = new LruCache<String, BitmapDrawable>(cacheSize) {
				@Override
				protected int sizeOf(String key, BitmapDrawable drawable) {
					return drawable.getBitmap().getByteCount();
				}
			};
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			String url = getItem(position);
			View view;
			if (convertView == null) {
				view = LayoutInflater.from(getContext()).inflate(R.layout.activity_load_imag_item, null);
			} else {
				view = convertView;
			}
			ImageView image = (ImageView) view.findViewById(R.id.image);
			BitmapDrawable drawable = getBitmapFromMemoryCache(url);
			if (drawable != null) {
				image.setImageDrawable(drawable);
			} else {
				BitmapWorkerTask task = new BitmapWorkerTask(image);
				task.execute(url);
			}
			return view;
		}

		/**
		 * 将一张图片存储到LruCache中。
		 * 
		 * @param key
		 *            LruCache的键，这里传入图片的URL地址。
		 * @param drawable
		 *            LruCache的值，这里传入从网络上下载的BitmapDrawable对象。
		 */
		public void addBitmapToMemoryCache(String key, BitmapDrawable drawable) {
			if (getBitmapFromMemoryCache(key) == null) {
				mMemoryCache.put(key, drawable);
			}
		}

		/**
		 * 从LruCache中获取一张图片，如果不存在就返回null。
		 * 
		 * @param key
		 *            LruCache的键，这里传入图片的URL地址。
		 * @return 对应传入键的BitmapDrawable对象，或者null。
		 */
		public BitmapDrawable getBitmapFromMemoryCache(String key) {
			return mMemoryCache.get(key);
		}

		/**
		 * 异步下载图片的任务。
		 * 
		 * @author guolin
		 */
		class BitmapWorkerTask extends AsyncTask<String, Void, BitmapDrawable> {

			private ImageView mImageView;

			public BitmapWorkerTask(ImageView imageView) {
				mImageView = imageView;
			}

			@Override
			protected BitmapDrawable doInBackground(String... params) {
				String imageUrl = params[0];
				// 在后台开始下载图片
				Bitmap bitmap = downloadBitmap(imageUrl);
				BitmapDrawable drawable = new BitmapDrawable(getContext().getResources(), bitmap);
				addBitmapToMemoryCache(imageUrl, drawable);
				return drawable;
			}

			@Override
			protected void onPostExecute(BitmapDrawable drawable) {
				if (mImageView != null && drawable != null) {
					mImageView.setImageDrawable(drawable);
				}
			}

			/**
			 * 建立HTTP请求，并获取Bitmap对象。
			 * 
			 * @param imageUrl
			 *            图片的URL地址
			 * @return 解析后的Bitmap对象
			 */
			private Bitmap downloadBitmap(String imageUrl) {
				Bitmap bitmap = null;
				HttpURLConnection con = null;
				try {
					URL url = new URL(imageUrl);
					con = (HttpURLConnection) url.openConnection();
					con.setConnectTimeout(5 * 1000);
					con.setReadTimeout(10 * 1000);
					bitmap = BitmapFactory.decodeStream(con.getInputStream());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (con != null) {
						con.disconnect();
					}
				}
				return bitmap;
			}

		}

	}

	public static class Images {

		public final static String[] imageUrls = new String[] {
				"http://img.my.csdn.net/uploads/201508/05/1438760758_3497.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760758_6667.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760757_3588.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760756_3304.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760755_6715.jpeg",
				"http://img.my.csdn.net/uploads/201508/05/1438760726_5120.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760726_8364.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760725_4031.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760724_9463.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760724_2371.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760707_4653.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760706_6864.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760706_9279.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760704_2341.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760704_5707.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760685_5091.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760685_4444.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760684_8827.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760683_3691.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760683_7315.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760663_7318.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760662_3454.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760662_5113.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760661_3305.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760661_7416.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760589_2946.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760589_1100.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760588_8297.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760587_2575.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760587_8906.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760550_2875.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760550_9517.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760549_7093.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760549_1352.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760548_2780.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760531_1776.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760531_1380.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760530_4944.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760530_5750.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760529_3289.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760500_7871.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760500_6063.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760499_6304.jpeg",
				"http://img.my.csdn.net/uploads/201508/05/1438760499_5081.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760498_7007.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760478_3128.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760478_6766.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760477_1358.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760477_3540.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760476_1240.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760446_7993.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760446_3641.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760445_3283.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760444_8623.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760444_6822.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760422_2224.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760421_2824.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760420_2660.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760420_7188.jpg",
				"http://img.my.csdn.net/uploads/201508/05/1438760419_4123.jpg", };
	}

}
