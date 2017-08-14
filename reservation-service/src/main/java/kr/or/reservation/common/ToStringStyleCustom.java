package kr.or.reservation.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringStyle;

public class ToStringStyleCustom extends ToStringStyle {

	private static final long serialVersionUID = 1L;

	/**
	 * The summary size text start <code>'&gt;'</code>.
	 */
	private String FIELD_NAME_PREFIX = "\"";

	/**
	 * <p>
	 * Constructor.
	 * </p>
	 *
	 * <p>
	 * Use the static constant rather than instantiating.
	 * </p>
	 */
	public ToStringStyleCustom() {
		super();

		this.setUseClassName(false);
		this.setUseIdentityHashCode(false);

		this.setContentStart("{");
		this.setContentEnd("}");

		this.setArrayStart("[");
		this.setArrayEnd("]");

		this.setFieldSeparator(",");
		this.setFieldNameValueSeparator(":");

		this.setNullText("null");

		this.setSummaryObjectStartText("\"<");
		this.setSummaryObjectEndText(">\"");

		this.setSizeStartText("\"<size=");
		this.setSizeEndText(">\"");
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, Object[] array, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, long[] array, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, int[] array, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, short[] array, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, byte[] array, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, char[] array, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, double[] array, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, float[] array, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, boolean[] array, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, array, fullDetail);
	}

	@Override
	public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}
		if (!isFullDetail(fullDetail)) {
			throw new UnsupportedOperationException("FullDetail must be true when using JsonToStringStyle");
		}

		super.append(buffer, fieldName, value, fullDetail);
	}

	@Override
	protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {

		if (value == null) {

			appendNullText(buffer, fieldName);
			return;
		}

		if (value.getClass() == String.class) {

			appendValueAsString(buffer, (String) value);
			return;
		}
		// 수정부분
		if (value.getClass() == Timestamp.class) {
			
			appendValueAsString(buffer, value.toString());
			return;
		}

		buffer.append(value);
	}

	/**
	 * Appends the given String in parenthesis to the given StringBuffer.
	 * 
	 * @param buffer
	 *            the StringBuffer to append the value to.
	 * @param value
	 *            the value to append.
	 */
	private void appendValueAsString(StringBuffer buffer, String value) {
		buffer.append("\"" + value + "\"");
	}

	@Override
	protected void appendFieldStart(StringBuffer buffer, String fieldName) {

		if (fieldName == null) {
			throw new UnsupportedOperationException("Field names are mandatory when using JsonToStringStyle");
		}

		super.appendFieldStart(buffer, FIELD_NAME_PREFIX + fieldName + FIELD_NAME_PREFIX);
	}

	/**
	 * <p>
	 * Ensure <code>Singleton</code> after serialization.
	 * </p>
	 *
	 * @return the singleton
	 */
	private Object readResolve() {
		return ToStringStyle.JSON_STYLE;
	}

}
