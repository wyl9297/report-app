package cn.bidlink.report.app.model.vo.order;

/**
 * @author <a href="mailto:xinyuli@ebnew.com">wisdom</a>
 * @version Ver 1.0
 * @description:订单分项报价项
 * @Date 2019/7/2
 */
public class OrderCargoItemQuteItemVO {

    /**
     * 分项报价key
     */
    private String key;

    /**
     *分项报价value
     */
    private String value;

    /**
     *分项报价标题
     */
    private String title;

    /**
     * 采购品id
     */
    private Long id;

    /**
     * @描述:采购品编码
     * @字段:code VARCHAR(100)
     */
    private String code;

    /**
     * 货单id
     */
    private Long cargoId;

    /**
     * 货单编号
     */
    private String cargoCode;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCargoId() {
        return cargoId;
    }

    public void setCargoId(Long cargoId) {
        this.cargoId = cargoId;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }
}
